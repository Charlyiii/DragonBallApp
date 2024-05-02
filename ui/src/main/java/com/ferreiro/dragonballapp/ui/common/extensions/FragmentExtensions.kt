package com.ferreiro.dragonballapp.ui.common.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ferreiro.dragonballapp.domain.model.error.ErrorModel

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toErrorMessage(errorModel: ErrorModel): String {
    return when (errorModel) {
        //TODO create more specific errors
        is ErrorModel.CommonError -> errorModel.message
    }
}
