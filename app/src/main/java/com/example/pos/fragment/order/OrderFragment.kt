package com.example.pos.fragment.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.pos.R
import com.example.pos.adapter_fragment.OrderFragAdapter
import com.example.pos.database.DataOrder
import com.example.pos.database.VolleySingleton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var orderData = arrayListOf<DataOrder>()
    var arrayUid = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = "http://192.168.137.1/pos/pos_api/public/orders"
        val jsonRequest = JsonArrayRequest(Request.Method.GET, url, null, {
            response ->

            for (i in 0 until response.length()) {

                val order = response.getJSONObject(i)

                arrayUid.add(order.get("user_id").toString())
                orderData.add(DataOrder(order.get("user_id") as Int, order.get("branch_id") as Int, order.get("staff_id") as Int, order.get("created_at").toString(), order.get("updated_at").toString()))
            }

            val recyclerView = view?.findViewById(R.id.recycler_order) as RecyclerView
            val adapter = OrderFragAdapter(orderData)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }, {
            Log.e("OrderFragment", "Error: $it")
        })

        jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
        activity?.let { VolleySingleton.getInstance(it).addToRequestQueue(jsonRequest) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_order, container, false)
        return view
    }
}