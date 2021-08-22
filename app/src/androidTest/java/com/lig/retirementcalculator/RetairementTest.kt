package com.lig.retirementcalculator


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After


@LargeTest
@RunWith(AndroidJUnit4::class)
class RetairementTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //add rule app center to log in cloud
    //need to install app center CLI to upload test
    @Rule
    @JvmField
    var reportHelper = Factory.getReportHelper()

    @Test
    fun retairementTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.monthlySavingsEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("200"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.interestEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("1.5"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.ageEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("50"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.retirementEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("6"), closeSoftKeyboard())

        val appCompatEditText5 = onView(
            allOf(
                withId(R.id.currentEditText),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText5.perform(replaceText("200"), closeSoftKeyboard())

        val appCompatEditText6 = onView(
            allOf(
                withId(R.id.retirementEditText), withText("6"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText6.perform(replaceText("60"))

        val appCompatEditText7 = onView(
            allOf(
                withId(R.id.retirementEditText), withText("60"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatEditText7.perform(closeSoftKeyboard())

        val appCompatEditText8 = onView(
            allOf(
                withId(R.id.currentEditText), withText("200"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText8.perform(replaceText("20000"))

        val appCompatEditText9 = onView(
            allOf(
                withId(R.id.currentEditText), withText("20000"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        appCompatEditText9.perform(closeSoftKeyboard())

        val materialButton = onView(
            allOf(
                withId(R.id.calculateButton), withText("Calculate retirement"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.resultTextView),
                withText("At current rate of 1.5, we have future saving 3600.0 yearly"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("At current rate of 1.5, we have future saving 3600.0 yearly")))
    }

    @After
    fun tearDown(){
        reportHelper.label("Finishing Test")
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
