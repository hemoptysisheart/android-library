package com.github.hemoptysisheart.sample.ui.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainScope
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope

@Composable
fun ConstraintLayoutScope.Pillar(
    constraint: ConstrainedLayoutReference,
    size: Int = PILLAR_SIZE,
    color: Color = Color.Black,
    constraintBlock: ConstrainScope.() -> Unit
) {
    Box(
        Modifier
            .constrainAs(constraint, constraintBlock)
            .size(size.dp)
            .background(color)
    )
}