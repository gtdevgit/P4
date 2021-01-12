package com.lamzone.mareu;

import android.content.Context;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.lamzone.mareu.di.DI;
import com.lamzone.mareu.service.ReunionApiService;
import com.lamzone.mareu.utils.MatcherViewHelper;
import com.lamzone.mareu.utils.RecyclerViewHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lamzone.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MareuInstrumentedTest {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.lamzone.mareu", appContext.getPackageName());
    }

    @Test
    public void listMetting_shouldNotBeEmpty() {
        onView(withId(R.id.reunion_recyclerview)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void listMetting_shouldShowAllMetting() {
        ReunionApiService service = DI.getNewInstanceApiService();
        int count = service.getReunions().size();
        onView(withId(R.id.reunion_recyclerview)).check(withItemCount(count));
    }

    @Test
    public void menuFilteredBySalle_shouldShowSalle() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        MatcherViewHelper.childAtPosition(
                                MatcherViewHelper.childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("Filtrer par salle"),
                        MatcherViewHelper.childAtPosition(
                                MatcherViewHelper.childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        MatcherViewHelper.childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(1); // Index dans la liste des salles
        materialTextView2.perform(click());
        // Doit afficher "Diderot" dans le premier item
        onView(withId(R.id.reunion_recyclerview))
                .check(matches(RecyclerViewHelper.atPosition(0,
                        hasDescendant(withText("Diderot")))));
    }

    /*
    @Test
    public void listMetting_FilteredByDate_shouldShowDate() {
        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        MenuHelper.childAtPosition(
                                MenuHelper.childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()));
        SystemClock.sleep(500);

        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("Filtrer par date"),
                        MenuHelper.childAtPosition(
                                MenuHelper.childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        SystemClock.sleep(500);

        materialTextView.perform(click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Previous month"),
                        MenuHelper.childAtPosition(
                                allOf(withClassName(is("android.widget.DayPickerView")),
                                        MenuHelper.childAtPosition(
                                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                                0)),
                                1)));
        SystemClock.sleep(500);

        appCompatImageButton.perform(scrollTo(), click());

        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        MenuHelper.childAtPosition(
                                MenuHelper.childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        SystemClock.sleep(500);
        materialButton.perform(scrollTo(), click());
        SystemClock.sleep(500);

        ViewInteraction materialRadioButton = onView(
                allOf(withClassName(is("com.google.android.material.radiobutton.MaterialRadioButton")), withText("AM"),
                        MenuHelper.childAtPosition(
                                allOf(withClassName(is("android.widget.RadioGroup")),
                                        MenuHelper.childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        SystemClock.sleep(500);
        materialRadioButton.perform(click());
        SystemClock.sleep(500);

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        MenuHelper.childAtPosition(
                                MenuHelper.childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());
        SystemClock.sleep(500);

        // ne doit rester qu'un enregistrement
        onView(withId(R.id.reunion_recyclerview)).check((withItemCount(1)));
    }
    */

    @Test
    public void listMetting_deleteAction_shouldRemoveItem() {
        ReunionApiService service = DI.getNewInstanceApiService();
        // nombre initial d'élements
        int count = service.getReunions().size();
        // Click sur le bouton delete du premier element
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.button_delete),
                        MatcherViewHelper.childAtPosition(
                                MatcherViewHelper.childAtPosition(
                                        withId(R.id.reunion_recyclerview),
                                        0), // Index de l'element à supprimer
                                6),
                        isDisplayed()));
        appCompatImageButton.perform(click());
        // Dialogue confirme la suppression
        ViewInteraction materialButton = onView(
                allOf(withId(android.R.id.button1), withText("Oui"),
                        MatcherViewHelper.childAtPosition(
                                MatcherViewHelper.childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton.perform(scrollTo(), click());
        // on doit trouver 1 élément de moins
        onView(withId(R.id.reunion_recyclerview)).check((withItemCount(count - 1)));
    }

    @Test
    public void addMetting_shouldAddItem() {
        // bouton ajouter réunion
        ViewInteraction floatingActionButton = onView(withId(R.id.floatingActionButtonAdd));
        floatingActionButton.perform(click());
        // sujet
        ViewInteraction textInputSujet = onView(withId(R.id.input_sujet));
        textInputSujet.perform(replaceText("Assurance qualité"), closeSoftKeyboard());
        // spinner, select "Buffon"
        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.spinner_salle),
                        MatcherViewHelper.childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        MatcherViewHelper.childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(MatcherViewHelper.childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(5);
        linearLayout.perform(click());

        // saisie directe de la date dans la zone de text, n'utilise pas le DatePickerDialog qui pose pb avec expresso
        ViewInteraction textInputDate = onView(withId(R.id.input_date));
        textInputDate.perform(replaceText("12/01/2021 14:00"), closeSoftKeyboard());

        // saisie de deux emails
        ViewInteraction textInputMail = onView(withId(R.id.input_email));
        textInputMail.perform(replaceText("george@gmail.com"), closeSoftKeyboard());
        // ajout du premier mail
        ViewInteraction appCompatImageButtonAjouterMail = onView(
                allOf(withId(R.id.button_ajouter_email), withContentDescription("button ajouter email"),
                        MatcherViewHelper.childAtPosition(
                                allOf(withId(R.id.constraintLayout),
                                        MatcherViewHelper.childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatImageButtonAjouterMail.perform(click());
        // sasie du deuxiéme mail
        textInputMail.perform(replaceText("marise@gmail.com"), closeSoftKeyboard());
        appCompatImageButtonAjouterMail.perform(click());
        // validation de l'activité
        ViewInteraction materialButtonValider = onView(
                allOf(withId(R.id.button_valider_creer_reunion), withText("Valider"),
                        MatcherViewHelper.childAtPosition(
                                allOf(withId(R.id.linearlayout_validation_formulaire),
                                        MatcherViewHelper.childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                2)),
                                1),
                        isDisplayed()));
        materialButtonValider.perform(click());

        // Vérification. Se positionne sur le dernier item et le sujet
        ReunionApiService service = DI.getReunionApiService();
        int count = service.getReunions().size();
        // scroll sur le dernier element
        onView(withId(R.id.reunion_recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(count-1, scrollTo()));
        // Doit afficher "Diderot" dans le premier item
        onView(withId(R.id.reunion_recyclerview))
                .check(matches(RecyclerViewHelper.atPosition(count-1,
                        hasDescendant(withText("Assurance qualité")))));

    }

    @Test
    public void menuRemoveFilter_souldRemodeFilter() {
        ReunionApiService service = DI.getNewInstanceApiService();
        // nombre initial d'élements
        int count = service.getReunions().size();

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("More options"),
                        MatcherViewHelper.childAtPosition(
                                MatcherViewHelper.childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                0),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.title), withText("Supprimer le filtre"),
                        MatcherViewHelper.childAtPosition(
                                MatcherViewHelper.childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        materialTextView.perform(click());
        // on doit trouver tous les éléments du modèle
        onView(withId(R.id.reunion_recyclerview)).check((withItemCount(count)));
    }
}