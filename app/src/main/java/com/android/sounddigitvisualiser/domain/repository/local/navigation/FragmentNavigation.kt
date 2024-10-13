package com.android.sounddigitvisualiser.domain.repository.local.navigation

import androidx.fragment.app.Fragment

interface FragmentNavigation {
    fun navigationFrag( newFragment: Fragment, addToStack: Boolean)
}