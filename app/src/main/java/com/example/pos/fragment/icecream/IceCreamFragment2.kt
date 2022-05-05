package com.example.pos.fragment.icecream

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.pos.R
import com.example.pos.product.IceCream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IceCreamFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class IceCreamFragment2 : Fragment() {
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
        val iceCreamID = arguments?.getInt("icecream_id_int")?: 5001
        val iceCream = IceCream.createIceCreamList()

        val iceCreamNameTV = rootView.findViewById(R.id.productName) as TextView
        iceCreamNameTV.text = iceCream[iceCreamID - 5001].name

        val iceCream_idTV = rootView.findViewById(R.id.ProductID) as TextView
        iceCream_idTV.text = iceCreamID.toString()

        val iceCreamImg = rootView.findViewById(R.id.productImage) as ImageView

        when (iceCreamID) {
            5001 -> iceCreamImg.setImageResource(R.drawable.vanilla_icecream)
            5002 -> iceCreamImg.setImageResource(R.drawable.strawberry_icecream)
            5003 -> iceCreamImg.setImageResource(R.drawable.mint_icecream)
            5004 -> iceCreamImg.setImageResource(R.drawable.lemon_icecream)
            5005 -> iceCreamImg.setImageResource(R.drawable.chocolate_icecream)
            else -> {
                iceCreamImg.setImageResource(R.drawable.vanilla_icecream)
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
         * @return A new instance of fragment IceCreamFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IceCreamFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}