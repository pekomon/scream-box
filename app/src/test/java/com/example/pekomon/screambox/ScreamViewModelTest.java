package com.example.pekomon.screambox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ScreamViewModelTest {

    private ScreamBox mScreamBox;
    private Sound mSound;
    private ScreamViewModel mScreamViewModel;

    @Before
    public void setUp() throws Exception {
        mScreamBox = mock(ScreamBox.class);
        mSound = new Sound("assetpath");
        mScreamViewModel = new ScreamViewModel(mScreamBox);
        mScreamViewModel.setSound(mSound);
    }

    @Test
    public void testTitle() {
        assertThat(mScreamViewModel.getTitle(), is(mSound.getName()));
    }

    @Test
    public void testOnButtonClicked() {
        mScreamViewModel.onButtonClicked();
        verify(mScreamBox).play(mSound);
    }

    @Test
    public void testSoundIsCorrect() {
        assertThat(mScreamViewModel.getSound(), is(mSound));
    }
}