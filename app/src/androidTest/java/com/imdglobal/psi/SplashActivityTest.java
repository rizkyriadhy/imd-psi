package com.imdglobal.psi;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.imdglobal.psi.api.rest.EndPoint;
import com.imdglobal.psi.views.activities.SplashActivity;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by rizkyriadhy on 6/25/17.
 */
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest extends InstrumentationTestCase {
    @Rule
    public ActivityTestRule<SplashActivity> mActivityRule =
            new ActivityTestRule<>(SplashActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        server = new MockWebServer();
        server.start();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        EndPoint.DEVELOPMENT_DOMAIN = server.url("/").toString();
        EndPoint.PRODUCTION_DOMAIN = server.url("/").toString();
        EndPoint.STAGING_DOMAIN = server.url("/").toString();
        EndPoint.VERSION = "";
        EndPoint.ENVIRONMENT = "";
    }

    @Test
    public void testDataIsShown() throws Exception {
        String fileName = "success_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.sp_txt_desc))
                .check(matches(withText("Done")));
    }


    @Test
    public void testRetryShowsWhenError() throws Exception {
        String fileName = "failed_response.json";

        server.enqueue(new MockResponse()
                .setResponseCode(400)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withText("Oops…")).
                inRoot(withDecorView(
                        not(is(mActivityRule.getActivity().
                                getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

}
