package com.m57.hermescontrol

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

// ── Top-level screens (kept after refactor — see REFACTOR_PLAN.md §9.1) ──

@Serializable data object LandingScreen : NavKey

@Serializable data object AuthLoginScreen : NavKey

@Serializable data object ChatScreen : NavKey

@Serializable data object SettingsScreen : NavKey

@Serializable data object SkillsScreen : NavKey

@Serializable data object CronJobsScreen : NavKey

@Serializable data object GatewayScreen : NavKey

@Serializable data object HistoryScreen : NavKey

@Serializable data object ProfilesScreen : NavKey

@Serializable data object MemoryScreen : NavKey

@Serializable data object ModelScreen : NavKey

// ── Settings drill-down sub-pages ──────────────────────────────────────

@Serializable data object SettingsConnection : NavKey

@Serializable data object SettingsAppearance : NavKey

@Serializable data object SettingsChat : NavKey

@Serializable data object SettingsBehavior : NavKey

@Serializable data object SettingsAbout : NavKey
