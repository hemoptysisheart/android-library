package com.github.hemoptysisheart.ui.compose.preview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

/**
 * 문자열이 **있는** UI 컴포넌트용 미리보기.
 */
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.FUNCTION)
@Preview(name = "Light, Font  85%", fontScale = 0.85f, showBackground = true)
@Preview(name = "Light, Font 100%", fontScale = 1.0f, showBackground = true)
@Preview(name = "Light, Font 115%", fontScale = 1.15f, showBackground = true)
@Preview(name = "Light, Font 130%", fontScale = 1.3f, showBackground = true)
@Preview(name = "Light, Font 150%", fontScale = 1.5f, showBackground = true)
@Preview(name = "Light, Font 180%", fontScale = 1.8f, showBackground = true)
@Preview(name = "Light, Font 200%", fontScale = 2f, showBackground = true)
@Preview(
    name = "Dark, Font 100%",
    fontScale = 1.0f,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL
)
annotation class PreviewComponent
