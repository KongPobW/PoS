package com.example.pos.fragment.drink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.pos.R
import com.example.pos.product.Drink

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DrinkFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrinkFragment2 : Fragment() {
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
        val drinkID = arguments?.getInt("drink_id_int")?: 2001
        val drink = Drink.createDrinkList()

        val drinkNameTV = rootView.findViewById(R.id.productName) as TextView
        drinkNameTV.text = drink[drinkID - 2001].name

        val drink_idTV = rootView.findViewById(R.id.ProductID) as TextView
        drink_idTV.text = drinkID.toString()

        val drinkImg = rootView.findViewById(R.id.productImage) as ImageView

        when (drinkID) {
            2001 -> drinkImg.setImageResource(R.drawable.water)
            2002 -> drinkImg.setImageResource(R.drawable.pepsi)
            2003 -> drinkImg.setImageResource(R.drawable.milk)
            2004 -> drinkImg.setImageResource(R.drawable.tea)
            2005 -> drinkImg.setImageResource(R.drawable.beer)
            else -> {
                drinkImg.setImageResource(R.drawable.water)
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
         * @return A new instance of fragment DrinkFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DrinkFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}