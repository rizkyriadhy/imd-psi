package com.imdglobal.psi.views.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.imdglobal.psi.R;
import com.imdglobal.psi.api.ImdGlobalPSI;
import com.imdglobal.psi.api.ImdGlobalPSILocalData;
import com.imdglobal.psi.api.rest.Callback;
import com.imdglobal.psi.api.rest.data.PsiByDates;
import com.imdglobal.psi.utils.UtilImdGlobal;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rizkyriadhy on 21/06/17.
 */
public class SplashActivity extends Activity {

    private Context context;
    private TextView txtSplash;

    //Permission Marshmallow
    private int REQUEST_PERMISSION_MULTIPLE = 120;

    public static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean isRooted(Context context) {
        boolean isEmulator = !isRealDevice(context);
        String buildTags = Build.TAGS;
        if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
            return true;
        } else {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                return true;
            } else {
                file = new File("/system/xbin/su");
                return !isEmulator && file.exists();
            }
        }
    }

    public static boolean isRealDevice(Context context) {
        String androidId = android.provider.Settings.Secure.getString(context.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);
        SensorManager manager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        return (!isEmulator()
                && (androidId != null
                || !androidId.equals("")
                || !androidId.equals("null")
                || !androidId.equals("NULL"))
                && (Build.SERIAL != null
                || !Build.SERIAL.equals("")
                || !Build.SERIAL.equals(" ")
                || !Build.SERIAL.equals("null")
                || !Build.SERIAL.equals("NULL")
                || !Build.SERIAL.equals("unknown"))
                && !manager.getSensorList(Sensor.TYPE_ALL).isEmpty());
    }

    public static void showMessageOKCancel(Context context, String message, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", cancelListener)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        context = this;
        declareView();
        checkEmulator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void declareView() {
        txtSplash = (TextView) findViewById(R.id.sp_txt_desc);
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {

            List<String> permissionsNeeded = new ArrayList<>();

            final List<String> permissionsList = new ArrayList<>();
            if (!addPermission(permissionsList, Manifest.permission.ACCESS_FINE_LOCATION))
                permissionsNeeded.add(getString(R.string.permission_location));
            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                permissionsNeeded.add(getString(R.string.permission_storage));

            if (permissionsList.size() > 0) {
                if (permissionsNeeded.size() > 0) {
                    // Need Rationale
                    String message = getString(R.string.permission_grant) + " " + permissionsNeeded.get(0);
                    for (int i = 1; i < permissionsNeeded.size(); i++)
                        message = message + ", " + permissionsNeeded.get(i);
                    showMessageOKCancel(context, message,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                        requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                                REQUEST_PERMISSION_MULTIPLE);
                                    }
                                }
                            },
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    UtilImdGlobal.infoFix(context, getString(R.string.app_name), getString(R.string.permission_must), new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                }
                            });
                    return;
                }
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PERMISSION_MULTIPLE);
                return;
            }
        }

        initFirst();
    }

    private void initFirst() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfTime = new SimpleDateFormat("hh:mm:ss");
        final String date = df.format(new Date());
        final String time = dfTime.format(new Date());

        PsiByDates.Request request = new PsiByDates.Request(date);
        ImdGlobalPSI.getInstance(context).getPsiByDates(request, new Callback<PsiByDates.Response>() {
            @Override
            public void onSuccess(int code, PsiByDates.Response body) {
                txtSplash.setText("Done");
                ImdGlobalPSILocalData.savePsiDate(body);

                try {
                    PsiByDates.Request request = new PsiByDates.Request(date+"T"+time);
                    ImdGlobalPSI.getInstance(context).getPsiByDateTimes(request, new Callback<PsiByDates.Response>() {
                        @Override
                        public void onSuccess(int code, PsiByDates.Response body) {
                            txtSplash.setText("Done");
                            ImdGlobalPSILocalData.savePsiDateTime(body);
                            goToMain();
                        }

                        @Override
                        public void onFailed(int code, String message) {
                            txtSplash.setText("Failed");
                            new AlertDialog.Builder(context)
                                    .setTitle(R.string.error)
                                    .setMessage(message)
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            initFirst();
                                        }
                                    })
                                    .setNegativeButton("close", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            System.exit(0);
                                        }
                                    })
                                    .setCancelable(false)
                                    .create()
                                    .show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    txtSplash.setText("Failed");
                    new AlertDialog.Builder(context)
                            .setTitle(R.string.error)
                            .setMessage(R.string.error_something_wrong)
                            .setIcon(R.mipmap.ic_launcher)
                            .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    initFirst();
                                }
                            })
                            .setNegativeButton("close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    System.exit(0);
                                }
                            })
                            .setCancelable(false)
                            .create()
                            .show();
                }
            }

            @Override
            public void onFailed(int code, String message) {
                txtSplash.setText("Failed");
                new AlertDialog.Builder(context)
                        .setTitle(R.string.error)
                        .setMessage(message)
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                initFirst();
                            }
                        })
                        .setNegativeButton("close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
            }
        });


    }

    public void goToMain(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    private void checkEmulator() {
        if (!isRooted(context)) { // its not emulator & not rooted or debug mode
            checkPermission();
        } else { // its emulator & rooted
            new AlertDialog.Builder(context)
                    .setTitle(R.string.error)
                    .setMessage(R.string.error_emulator)
                    .setIcon(R.mipmap.ic_launcher)
                    .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkEmulator(

                            );
                        }
                    })
                    .setNegativeButton("close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!shouldShowRequestPermissionRationale(permission))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_MULTIPLE) {
            Map<String, Integer> perms = new HashMap<>();
            // Initial
            perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);

            // Fill with results
            for (int i = 0; i < permissions.length; i++)
                perms.put(permissions[i], grantResults[i]);
            // Check for ACCESS_FINE_LOCATION
            if (perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // All Permissions Granted
                initFirst();
            } else {
                // Permission Denied
                Toast.makeText(context, getString(R.string.error_permission), Toast.LENGTH_SHORT)
                        .show();
                UtilImdGlobal.infoFix(context, getString(R.string.app_name), getString(R.string.permission_must), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
            }

        }
    }
}