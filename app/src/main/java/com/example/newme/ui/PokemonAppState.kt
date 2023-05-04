package com.example.newme.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newme.ui.navigation.PokemonBattleDestination
import com.example.newme.ui.navigation.TopLevelDestination

@Composable
fun rememberPokemonAppState(
    navController: NavHostController = rememberNavController(),
): PokemonAppState {
    return remember(navController) {
        PokemonAppState(navController)
    }
}

class PokemonAppState(
    val navController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestination: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = PokemonBattleDestination.route,
            destinations = PokemonBattleDestination.route,
        )
    )

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * Top level destinations have only one copy of the destination of the back stack, and save and
     * restore state whenever you navigate to and from it.
     * Regular destinations can have multiple copies in the back stack and state isn't saved nor
     * restored.
     *
     * @param destination: The [PeopleInSpaceNavigationDestination] the app needs to navigate to.
     * @param route: Optional route to navigate to in case the destination contains arguments.
     */
//    fun navigate(destination: PokemonAppNavigationDestination, route: String? = null) {
//        if (destination is TopLevelDestination) {
//            navController.navigate(route ?: destination.route) {
//                // Pop up to the start destination of the graph to
//                // avoid building up a large stack of destinations
//                // on the back stack as users select items
//                popUpTo(navController.graph.findStartDestination().id) {
//                    saveState = true
//                }
//                // Avoid multiple copies of the same destination when
//                // reselecting the same item
//                launchSingleTop = true
//                // Restore state when reselecting a previously selected item
//                restoreState = true
//            }
//        } else {
//            navController.navigate(route ?: destination.route)
//        }
//    }

    fun onBackClick() {
        navController.popBackStack()
    }
}