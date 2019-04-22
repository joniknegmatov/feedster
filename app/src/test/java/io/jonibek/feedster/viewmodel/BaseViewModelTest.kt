package io.jonibek.feedster.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.rules.TestRule

abstract class BaseViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
}
