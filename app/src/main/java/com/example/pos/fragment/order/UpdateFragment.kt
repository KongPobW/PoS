package com.example.pos.fragment.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pos.R
import kotlinx.android.synthetic.main.fragment_update.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getString("id")
        val arg1 = arguments?.getString("oid")
        val arg2 = arguments?.getString("uid")
        val arg3 = arguments?.getString("pid")
        val arg4 = arguments?.getString("pNa")
        val arg5 = arguments?.getString("qty")
        val arg6 = arguments?.getString("sto")

        orID_intent.setText(arg1.toString())
        prID_intent.setText(arg3.toString())
        prNa_intent.setText(arg4.toString())
        quantity_intent.setText(arg5.toString())
        subtotal_intent.setText(arg6.toString())

        btn_update.setOnClickListener {

            val txt1 = orID_intent.text
            val txt2 = prID_intent.text
            val txt3 = prNa_intent.text
            val txt4 = quantity_intent.text
            val txt5 = subtotal_intent.text

            val queue = Volley.newRequestQueue(context)
            val url = "http://192.168.1.121/pos/pos_api/public/orderline/${id}?order_id=${txt1}&user_id=${arg2}&product_id=${txt2}&product_name=${txt3}&quantity=${txt4}&subtotal=${txt5}"
            val stringRequest = StringRequest(Request.Method.PUT, url, {
                response ->
                Log.i("UpdateFragment", "Updated")
                this.findNavController().navigate(R.id.orderfrag)
            }, {
                Log.e("UpdateFragment", "Failed")
                this.findNavController().navigate(R.id.orderfrag)
            })
            queue.add(stringRequest)
        }

        btn_cancel.setOnClickListener {
            this.findNavController().navigate(R.id.orderlinefrag)
        }
    }
}