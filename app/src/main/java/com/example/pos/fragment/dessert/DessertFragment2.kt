package com.example.pos.fragment.dessert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.pos.R
import com.example.pos.product.Dessert

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DessertFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class DessertFragment2 : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_product2, container, false)
        val dessertID = arguments?.getInt("dessert_id_int")?: 3001
        val dessert = Dessert.createDessertList()

        val dessertNameTV = rootView.findViewById(R.id.productName) as TextView
        dessertNameTV.text = dessert[dessertID - 3001].name

        val dessert_idTV = rootView.findViewById(R.id.ProductID) as TextView
        dessert_idTV.text = dessertID.toString()

        val dessertImg = rootView.findViewById(R.id.productImage) as ImageView

        when (dessertID) {
            3001 -> dessertImg.setImageResource(R.drawable.applepie)
            3002 -> dessertImg.setImageResource(R.drawable.bakedalaska)
            3003 -> dessertImg.setImageResource(R.drawable.bananafoster)
            3004 -> dessertImg.setImageResource(R.drawable.eggtarts)
            3005 -> dessertImg.setImageResource(R.drawable.lemontarts)
            else -> {
                dessertImg.setImageResource(R.drawable.applepie)
            }
        }
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DessertFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DessertFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}