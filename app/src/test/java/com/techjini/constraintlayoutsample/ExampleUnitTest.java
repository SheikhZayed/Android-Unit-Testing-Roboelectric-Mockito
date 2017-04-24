package com.techjini.constraintlayoutsample;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleUnitTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    ActivityController controller;
    MainActivity activity;

    @Mock
    RandomModel model;

    @Mock
    ArrayList<String> mockedList;

    @Captor
    ArgumentCaptor<List<String>> argumentCaptor;

    @Before
    public void setup() {
        controller = Robolectric.buildActivity(MainActivity.class).create().start();
        activity = (MainActivity) controller.get();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void clickingButton_shouldShowToast() throws Exception {
        activity.findViewById(R.id.button).performClick();
        //test for same toast or not
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("thanks,for clicking button"));
    }


    @Test
    public void method() throws Exception {
        //test if method returns same value
        assertEquals(activity.someMethod(4, 6), 10);
    }

    @Test
    public void testOnPauseCalled() throws Exception {
        //testing onpause is called correctly or not
        controller.pause();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("onpause"));

    }

    @Test
    public void testActivityNotNull() throws Exception {
        //fails if activty context is null
        assertNotNull(activity);
    }

    @Test
    public void testOnRestartCalled() throws Exception {
        controller.restart();
        assertThat(ShadowToast.getTextOfLatestToast(), equalTo("onrestart"));
//        verify(activity.restartWork());
     //   assertEquals(activity.RESTART_WORK, /*activity.restartWork()*/"restart_work");
    }

    @Test
    public void testMyMethodCalledCorrectly() throws Exception {
        when(model.dumbMethod()).thenReturn(7);
        assertEquals(model.dumbMethod(), 7);

        /*verifying that anonymous() is never called  on this mock object*/

        verify(model, never()).anonymousMethod();
    }

    @Test
    public void testDumbMethodCalledCorrectly() throws Exception {
        when(model.randomMethod()).thenReturn(true);
        RandomModel model = new RandomModel();
        assertTrue(model.randomMethod());
    }

    @Test
    public void testMethodWhichReturnsString() throws Exception {
        when(model.methodWhichReturnString(anyString())).thenReturn("returned something");

        /*should fail,if return value change*/
        assertEquals(model.methodWhichReturnString(anyString()), "returned something");
    }

    @Test
    public void testSampleMethod() {
        when(model.sampleMethod()).thenReturn("ashif");

        /*checks if sample method is never called on this mock,else which the test fails*/
        verify(model, never()).sampleMethod();

        assertEquals(model.sampleMethod(), "ashif");
        /*calls the smaple method 1 time */
        verify(model, times(1)).sampleMethod();


        assertEquals(model.sampleMethod(), "ashif");
        assertEquals(model.sampleMethod(), "ashif");
        assertEquals(model.sampleMethod(), "ashif");

        /*checks if sample method is called atmost 4 times on this mock,else which the test fails*/
        verify(model, atMost(5)).sampleMethod();

        /*checks if sample method is called atleast once*/
        verify(model,atLeastOnce()).sampleMethod();
    }

    @Test
    public void testArgumentCaptor(){
        List<String> myList = Arrays.asList("ashif","kerala","random");
        mockedList.addAll(myList);

        verify(mockedList).addAll(argumentCaptor.capture());
        final List<String> capturedArgument = argumentCaptor.getValue();
        /*will pass*/
//        assertThat(capturedArgument, hasItem("ashif"));
        /*will fail*/
        assertThat(capturedArgument, hasItem("ashif"));
    }

    @Test
    public void testMethodArgumentMatcher(){
        //when(model.methodWhichReturnString("jason")).thenReturn("hi json");
        model.methodWhichReturnString("jason");

        /*testing if the method is called with jason as arguments*/

        verify(model).methodWhichReturnString(ArgumentMatchers.eq("jason"));
    }

    @Test
    public void testObjectReturn() {

        RandomModel randomModel = mock(RandomModel.class);
        randomModel.setAge(11);
        randomModel.setName("name");

        RandomModel randomModel1 = mock(RandomModel.class);
        randomModel1.setAge(11);
        randomModel1.setName("name");

        when(model.returnsObject()).thenReturn(randomModel);
        assertSame(randomModel,randomModel1);


    }
}
