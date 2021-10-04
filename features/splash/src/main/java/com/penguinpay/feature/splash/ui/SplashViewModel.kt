package com.penguinpay.feature.splash.ui

import com.penguinpay.domain.user.GetUserPreferencesUseCase
import com.penguinpay.domain.user.entity.UserThemeStyle
import com.penguinpay.feature.splash.ui.SplashViewModel.SlashViewState
import com.penguinpay.feature.splash.ui.SplashViewModel.SplashViewAction
import com.penguinpay.libraries.coroutines.android.CoroutineService
import com.penguinpay.libraries.coroutines.android.CoroutinesViewModel

internal class SplashViewModel(
    coroutineService: CoroutineService,
    private val getUserPreferencesUseCase: GetUserPreferencesUseCase,
) : CoroutinesViewModel<SlashViewState, SplashViewAction>(coroutineService, SlashViewState()) {

    class SlashViewState

    sealed class SplashViewAction {
        object SetDefaultNightModeAlwaysLight : SplashViewAction()
        object SetDefaultNightModeAlwaysDark : SplashViewAction()
        object SetDefaultNightModeFollowSystem : SplashViewAction()
        object GoToHome : SplashViewAction()
    }

    fun onCreate() {
        scope.launchIdling {
            with(getUserPreferencesUseCase()) {
                _action.value = when (themeStyle) {
                    UserThemeStyle.AlwaysDark -> SplashViewAction.SetDefaultNightModeAlwaysDark
                    UserThemeStyle.AlwaysLight -> SplashViewAction.SetDefaultNightModeAlwaysLight
                    UserThemeStyle.Android -> SplashViewAction.SetDefaultNightModeFollowSystem
                }
            }

            _action.value = SplashViewAction.GoToHome
        }
    }
}