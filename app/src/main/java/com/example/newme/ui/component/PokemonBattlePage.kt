package com.example.newme.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newme.R
import com.example.newme.ui.model.PokemonBattlePageUiState
import com.example.newme.ui.viewmodel.PokemonBattleViewModel

@Composable
fun PokemonBattleRoute(
    viewModel: PokemonBattleViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    PokemonBattlePage(
        uiState
    )

}

@Composable
fun PokemonBattlePage(
    uiState: PokemonBattlePageUiState,
    modifier: Modifier = Modifier,
    onFightClicked: () -> Unit = {},
    onPkmnClicked: () -> Unit = {},
    onItemClicked: () -> Unit = {},
    onRunClicked: () -> Unit = {},
    onMenuBackClicked: () -> Unit = {},
) {
    val battleMenuState = uiState.battleMenuState
    val userTeam = uiState.userTeam
    val enemyTeam = uiState.enemyTeam

    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            PokemonBattleOpponent(
                pokemonName = enemyTeam[0].name,
                pokemonLevel = enemyTeam[0].level,
                percentageHealth = enemyTeam[0].percentageHealth,
                healthBarColor = enemyTeam[0].healthBarColor,
                pokemonImgUrl = enemyTeam[0].sprites.frontDefault ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
            )
            PokemonBattleUser(
                pokemonName = userTeam[0].name,
                pokemonLevel = userTeam[0].level,
                percentageHealth = userTeam[0].percentageHealth,
                healthBarColor = userTeam[0].healthBarColor,
                pokemonImageUrl = userTeam[0].sprites.backDefault ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
            )
            when (battleMenuState) {
                com.example.newme.ui.viewmodel.BattleMenu.MAIN -> {
                    BattleMenu(
                        onFightClicked = { onFightClicked() },
                        onPkmnClicked = {  },
                        onItemClicked = { /*TODO*/ },
                        onRunClicked = {  }
                    )
                }
                com.example.newme.ui.viewmodel.BattleMenu.ATTACK -> {
                    AttackMenu(
                        attack1 = userTeam[0].moves[0].move.name,
                        attack2 = userTeam[0].moves[1].move.name ?: "testing moves",
                        onAttack1Clicked = { /*TODO*/ },
                        onAttack2Clicked = { /*TODO*/ },
                        onBackClicked = { onMenuBackClicked() }
                    )
                }
            }

        }
    }
}

@Composable
fun PokemonBattleOpponent(
    modifier: Modifier = Modifier,
    pokemonName: String,
    pokemonLevel: Int,
    percentageHealth: Float,
    healthBarColor: Color,
    pokemonImgUrl: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = modifier
            .padding(5.dp)
    ) {
        PokemonName(
            name = pokemonName,
        )
        PokemonLevel(
            level = pokemonLevel,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HealthBar(
                percentageHealth = percentageHealth,
                color = healthBarColor,
            )
        }
        Row() {
            Spacer(modifier = Modifier.weight(1f))
            PokemonImage(
                imgUrl = pokemonImgUrl,
            )
        }
    }
}

@Composable
fun PokemonBattleUser(
    modifier: Modifier = Modifier,
    pokemonName: String,
    pokemonLevel: Int,
    percentageHealth: Float,
    healthBarColor: Color,
    pokemonImageUrl: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = modifier
            .padding(5.dp)
    ) {
        PokemonImage(
            imgUrl = pokemonImageUrl,
        )
        Row {
            Spacer(modifier = Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                PokemonName(
                    name = pokemonName,
                )
                PokemonLevel(
                    level = pokemonLevel,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HealthBar(
                        percentageHealth = percentageHealth,
                        color = healthBarColor,
                    )
                }
            }
        }
    }
}

// Composable Pieces

@Composable
fun PokemonName(
    modifier: Modifier = Modifier,
    name: String = "SQUIRTLE",
) {
    Text(
        text = name,
        fontSize = 20.sp,
        fontFamily = FontFamily.Default,
        modifier = modifier,
    )
}

@Composable
fun PokemonLevel(
    modifier: Modifier = Modifier,
    level: Int = 0,
) {
    Text(
        text = ":L $level",
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .padding(start = 40.dp)
    )

}

@Composable
fun HealthBar(
    percentageHealth: Float,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,

    ) {
        Text(
            text = "HP:",
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    end = 4.dp,
                )
        )
        LinearProgressIndicator(
            progress = percentageHealth,
            color = color,
            modifier = Modifier
                .width(150.dp)
        )
    }
}

@Composable
fun PokemonImage(
    modifier: Modifier = Modifier,
    imgUrl: String,
    placeHolder: Painter = painterResource(id = R.drawable.pokeball)
) {

    val imagePainter = rememberAsyncImagePainter(
        model = imgUrl,
        placeholder = placeHolder,

    )

    Image(
        painter = imagePainter,
        contentDescription = "Pokemon Placeholder Image",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(75.dp)
    )
}

@Composable
fun BattleMenu(
    modifier: Modifier = Modifier,
    onFightClicked: () -> Unit,
    onPkmnClicked: () -> Unit,
    onItemClicked: () -> Unit,
    onRunClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            MenuBox(
                menuName = "FIGHT",
                onClick = onFightClicked,
            )
            MenuBox(
                menuName = "PKMN",
                onClick = onPkmnClicked,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1F)

        ) {
            MenuBox(
                menuName = "ITEM",
                onClick = onItemClicked,
            )
            MenuBox(
                menuName = "RUN",
                onClick = onRunClicked,
            )
        }
    }
}

@Composable
fun AttackMenu(
    modifier: Modifier = Modifier,
    attack1: String,
    attack2: String,
    onAttack1Clicked: () -> Unit,
    onAttack2Clicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .weight(1F)
                .fillMaxSize()
        ) {
            MenuBox(
                menuName = attack1,
                onClick = onAttack1Clicked,
            )
            MenuBox(
                menuName = attack2,
                onClick = onAttack2Clicked,
            )
        }
        Row(
            modifier = Modifier
                .weight(1F)
        ) {
            MenuBox(
                menuName = "N/A",
                onClick = {},
            )
            MenuBox(
                menuName = "Back",
                onClick = { onBackClicked() },
            )
        }
    }
}

@Composable
 fun RowScope.MenuBox(
    menuName: String,
    onClick: () -> Unit,
 ) {
    ClickableText(
        text = AnnotatedString(menuName),
        onClick = { onClick() },
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
        ),
        modifier = Modifier
            .weight(1F)
            .fillMaxSize()
            .border(
                border = BorderStroke(2.dp, Color.Black)
            )
    )
}

@Preview
@Composable
fun HealthBarPreview() {
    PokemonBattlePage(PokemonBattlePageUiState())
}
