package com.softtanck.composedemo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.softtanck.composedemo.R


@Composable
fun UserInputEditText(onInputDone: (String) -> Unit) {
    var innerUserInputString by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    BasicTextField(
        value = innerUserInputString,
        onValueChange = { innerUserInputString = it },
        Modifier
            .padding(10.dp)
            .size(300.dp, 50.dp),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .background(
                        color = Color.DarkGray,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                if (innerUserInputString.isEmpty()) {
                    Text(text = stringResource(R.string.hint), color = Color.Red)
                }
                innerTextField()
            }
        },
        singleLine = true,
        maxLines = 1,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        keyboardActions = KeyboardActions(onAny = {
            focusManager.clearFocus()
            onInputDone.invoke(innerUserInputString)
        })
    )
}
