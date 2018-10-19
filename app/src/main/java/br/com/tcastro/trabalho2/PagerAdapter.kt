package br.com.tcastro.trabalho2

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(p0: Int): Fragment {
        when(p0) {
            0 -> return PrimeiroFragment()
            1 -> return SegundoFragment()
            2 -> return TerceiroFragment()
            else -> return PrimeiroFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Fragment ${position + 1}"
    }
}