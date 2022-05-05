package com.example.pos.fragment.macaron

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.R
import com.example.pos.adapter_fragment.MacaronFragAdapter
import com.example.pos.product.Macaron

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MacaronFragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_product1, container, false)
        val rvMacaron = layout.findViewById(R.id.rvListProductFragment) as RecyclerView
        val macaron = Macaron.createMacaronList()

        val adapterFragMacaron = MacaronFragAdapter(macaron) { macarons ->

            val bundle = bundleOf("macaron_id_int" to macarons.id)
            val detailFragment = MacaronFragment2()
            detailFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()

            val view: FragmentContainerView? = activity?.findViewById(R.id.detail_product_fragmentContainerView)

            if (view != null) {
                transaction.replace(R.id.detail_product_fragmentContainerView, detailFragment)
            } else {
                transaction.replace(R.id.list_product_fragmentContainerView, detailFragment)
            }

            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }
        rvMacaron.layoutManager = LinearLayoutManager(layout.context)
        rvMacaron.adapter = adapterFragMacaron

        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MacaronFragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}