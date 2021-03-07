package br.fernando.simulaparcela.Adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.fernando.simulaparcela.principal.FirstFragment
import br.fernando.simulaparcela.principal.SecondFragment
import br.fernando.simulaparcela.principal.ThreeFragment

class PagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0->{
                FirstFragment()
            }
            1->{
                SecondFragment()
            }
            else->{ThreeFragment()}
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Empréstimo"
            1->"Distribuição"
            else->"Revitalização"
        }
    }
}