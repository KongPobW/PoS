package com.example.pos.fragment.thaidish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.pos.R
import com.example.pos.product.ThaiDish

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThaiDishFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThaiDishFragment2 : Fragment() {
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
        val thaiDishID = arguments?.getInt("thaidish_id_int")?: 6001
        val thaiDish = ThaiDish.createThaiDishList()

        val thaiDishNameTV = rootView.findViewById(R.id.productName) as TextView
        thaiDishNameTV.text = thaiDish[thaiDishID - 6001].name

        val thaiDish_idTV = rootView.findViewById(R.id.ProductID) as TextView
        thaiDish_idTV.text = thaiDishID.toString()

        val thaiDishImg = rootView.findViewById(R.id.productImage) as ImageView

        when (thaiDishID) {
            6001 -> thaiDishImg.setImageResource(R.drawable.guayteow)
            6002 -> thaiDishImg.setImageResource(R.drawable.laab)
            6003 -> thaiDishImg.setImageResource(R.drawable.somtam)
            6004 -> thaiDishImg.setImageResource(R.drawable.tomkhagai)
            6005 -> thaiDishImg.setImageResource(R.drawable.tomyum)
            else -> {
                thaiDishImg.setImageResource(R.drawable.guayteow)
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
         * @return A new instance of fragment ThaiDishFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThaiDishFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}