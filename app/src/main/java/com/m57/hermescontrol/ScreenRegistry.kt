package com.m57.hermescontrol

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import com.m57.hermescontrol.ui.chat.ChatScreen as ChatScreenContent
import com.m57.hermescontrol.ui.cron.CronJobsScreen as CronJobsScreenContent
import com.m57.hermescontrol.ui.gateway.GatewayScreen as GatewayScreenContent
import com.m57.hermescontrol.ui.memory.MemoryScreen as MemoryScreenContent
import com.m57.hermescontrol.ui.model.ModelScreen as ModelScreenContent
import com.m57.hermescontrol.ui.profiles.ProfilesScreen as ProfilesScreenContent
import com.m57.hermescontrol.ui.sessions.SessionsScreen as HistoryScreenContent
import com.m57.hermescontrol.ui.settings.SettingsScreen as SettingsScreenContent
import com.m57.hermescontrol.ui.skills.SkillsScreen as SkillsScreenContent
import com.m57.hermescontrol.ui.common.NeurologyIcon

/**
 * Refactored for the personal "chat + sessions" focus.
 * 17 of 25 screens removed — see REFACTOR_PLAN.md §9.1.
 */
enum class DrawerSection(
    @param:StringRes val titleRes: Int,
) {
    CONVERSE(R.string.nav_drawer_section_converse),
    AUTOMATE(R.string.nav_drawer_section_automate),
    INSPECT(R.string.nav_drawer_section_inspect),
}

data class ScreenDefinition(
    val key: NavKey,
    @param:StringRes val labelRes: Int,
    val icon: ImageVector,
    val drawerSection: DrawerSection?,
    val content: @Composable (sessionId: String?, openDrawer: () -> Unit) -> Unit,
)

object ScreenRegistry {
    val ALL_SCREENS =
        listOf(
            // ── CONVERSE ──
            ScreenDefinition(
                ChatScreen,
                R.string.screen_chat,
                Icons.AutoMirrored.Filled.Chat,
                DrawerSection.CONVERSE,
            ) { sessionId, openDrawer -> ChatScreenContent(onOpenDrawer = openDrawer, sessionId = sessionId) },
            ScreenDefinition(
                HistoryScreen,
                R.string.screen_history,
                Icons.Filled.History,
                DrawerSection.CONVERSE,
            ) { sessionId, openDrawer -> HistoryScreenContent(onOpenDrawer = openDrawer) },
            ScreenDefinition(
                ProfilesScreen,
                R.string.screen_profiles,
                Icons.Filled.AccountCircle,
                DrawerSection.CONVERSE,
            ) { sessionId, openDrawer -> ProfilesScreenContent(onOpenDrawer = openDrawer) },
            // ── AUTOMATE ──
            ScreenDefinition(
                CronJobsScreen,
                R.string.screen_cron,
                Icons.Filled.Schedule,
                DrawerSection.AUTOMATE,
            ) { sessionId, openDrawer -> CronJobsScreenContent(onOpenDrawer = openDrawer) },
            ScreenDefinition(
                SkillsScreen,
                R.string.screen_skills,
                Icons.Filled.Inventory2,
                DrawerSection.AUTOMATE,
            ) { sessionId, openDrawer -> SkillsScreenContent(onOpenDrawer = openDrawer) },
            ScreenDefinition(
                ModelScreen,
                R.string.screen_models,
                Icons.Filled.Psychology,
                DrawerSection.AUTOMATE,
            ) { sessionId, openDrawer -> ModelScreenContent(onOpenDrawer = openDrawer) },
            ScreenDefinition(
                MemoryScreen,
                R.string.screen_memory,
                NeurologyIcon,
                DrawerSection.AUTOMATE,
            ) { sessionId, openDrawer -> MemoryScreenContent(onOpenDrawer = openDrawer) },
            // ── INSPECT ──
            ScreenDefinition(
                GatewayScreen,
                R.string.screen_gateway,
                Icons.Filled.Bolt,
                DrawerSection.INSPECT,
            ) { sessionId, openDrawer -> GatewayScreenContent(onOpenDrawer = openDrawer) },
            ScreenDefinition(
                SettingsScreen,
                R.string.screen_settings,
                Icons.Filled.Settings,
                DrawerSection.INSPECT,
            ) { sessionId, openDrawer ->
                SettingsScreenContent(
                    onOpenDrawer = openDrawer,
                    onNavigateToLogin = {
                        NavigationController.navigateTo(AuthLoginScreen)
                    },
                )
            },
        )
}
