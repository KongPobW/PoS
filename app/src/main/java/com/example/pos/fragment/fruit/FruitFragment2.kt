package com.example.pos.fragment.fruit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.pos.R
import com.example.pos.product.Fruit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FruitFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class FruitFragment2 : Fragment() {
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
        val fruitID = arguments?.getInt("fruit_id_int")?: 4001
        val fruit = Fruit.createFruitList()

        val fruitNameTV = rootView.findViewById(R.id.productName) as TextView
        fruitNameTV.text = fruit[fruitID - 4001].name

        val fruit_idTV = rootView.findViewById(R.id.ProductID) as TextView
        fruit_idTV.text = fruitID.toString()

        val fruitImg = rootView.findViewById(R.id.productImage) as ImageView

        when (fruitID) {
            4001 -> fruitImg.setImageResource(R.drawable.watermelon)
            4002 -> fruitImg.setImageResource(R.drawable.apple)
            4003 -> fruitImg.setImageResource(R.drawable.pineapple)
            4004 -> fruitImg.setImageResource(R.drawable.banana)
            4005 -> fruitImg.setImageResource(R.drawable.papaya)
            else -> {
                fruitImg.setImageResource(R.drawable.watermelon)
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
         * @return A new instance of fragment FruitFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FruitFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}