package com.m57.hermescontrol.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.m57.hermescontrol.theme.presets.DefaultDarkColorScheme
import com.m57.hermescontrol.theme.presets.DefaultDarkStatusColors
import com.m57.hermescontrol.theme.presets.DefaultLightColorScheme
import com.m57.hermescontrol.theme.presets.DefaultLightStatusColors
import kotlinx.serialization.Serializable

@Serializable
enum class ThemePreference { SYSTEM, LIGHT, DARK }

/**
 * Single theme preset — refactored from 6 (DEFAULT, MONOCHROME, GRUVBOX, CATPPUCCIN, AMOLED, NEON_NOIR)
 * down to 1. Personal app, no theme switcher needed.
 */
@Serializable
enum class ThemePreset { DEFAULT }

val LocalThemePreference = compositionLocalOf { ThemePreference.SYSTEM }
val LocalThemePreset = compositionLocalOf { ThemePreset.DEFAULT }

private fun resolveColorScheme(
    preset: ThemePreset,
    darkTheme: Boolean,
) = when (preset) {
    ThemePreset.DEFAULT -> if (darkTheme) DefaultDarkColorScheme else DefaultLightColorScheme
}

private fun resolveStatusColors(
    preset: ThemePreset,
    darkTheme: Boolean,
) = when (preset) {
    ThemePreset.DEFAULT -> if (darkTheme) DefaultDarkStatusColors else DefaultLightStatusColors
}

@Composable
fun HermesControlTheme(
    themePreference: ThemePreference = LocalThemePreference.current,
    useDynamicColors: Boolean = false, // disabled in personal refactor — fixed brand identity
    themePreset: ThemePreset = ThemePreset.DEFAULT,
    content: @Composable () -> Unit,
) {
    val darkTheme =
        when (themePreference) {
            ThemePreference.SYSTEM -> isSystemInDarkTheme()
            ThemePreference.LIGHT -> false
            ThemePreference.DARK -> true
        }

    val context = LocalContext.current
    val dynamicAvailable =
        useDynamicColors && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme =
        if (dynamicAvailable) {
            if (darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        } else {
            resolveColorScheme(themePreset, darkTheme)
        }

    val statusColors = resolveStatusColors(themePreset, darkTheme)

    CompositionLocalProvider(
        LocalThemePreference provides themePreference,
        LocalThemePreset provides themePreset,
        LocalHermesStatusColors provides statusColors,
        LocalSpacing provides SpacingDefaults,
        LocalMotion provides MotionDefaults,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = HermesShapes,
            content = content,
        )
    }
}
