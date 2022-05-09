package com.example.pos.fragment.order

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.pos.R
import com.example.pos.adapter_fragment.OrderLineFragAdapter
import com.example.pos.database.DataOrder
import com.example.pos.database.DataOrderLine
import com.example.pos.database.VolleySingleton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderlineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderLineFragment : Fragment() {

    companion object {
        var state: String? = null
    }

    var orderLineData = arrayListOf<DataOrderLine>()
    var arrayUid = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    private fun getData() {
        val url = "http://192.168.1.121/pos/pos_api/public/orderlines"
        val jsonRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->

            for (i in 0 until response.length()) {

                val orderline = response.getJSONObject(i)

                arrayUid.add(orderline.get("user_id").toString())
                orderLineData.add(
                    DataOrderLine(
                        orderline.get("id") as Int,
                        orderline.get("order_id") as Int,
                        orderline.get("user_id") as Int,
                        orderline.get("product_id") as Int,
                        orderline.get("product_name") as String,
                        orderline.get("quantity") as Int,
                        orderline.get("subtotal") as Int,
                    )
                )
            }

            val recyclerView = view?.findViewById(R.id.recycler_orderline) as RecyclerView
            val adapter = OrderLineFragAdapter(orderLineData) { orderlinedata ->

                if (state.equals("DeleteButton")) {
                    val orderlineSize = arrayUid.filter { data -> data == orderlinedata.uid.toString() }.size
                    println("orderlinesize" + orderlineSize)
                    if (orderlineSize == 1) {
                        val q1 = Volley.newRequestQueue(context)
                        val u1 = "http://192.168.1.121/pos/pos_api/public/order/${orderlinedata.uid}"
                        val s1 = StringRequest(Request.Method.DELETE, u1, {
                            response ->

                            orderLineData.clear()
                            arrayUid.clear()
                        }, {
                            Log.e("OrderLineFragment", "Error: $it")
                        })
                        q1.add(s1)

                        val q2 = Volley.newRequestQueue(context)
                        val u2 = "http://192.168.1.121/pos/pos_api/public/orderline/${orderlinedata.id}"
                        val s2 = StringRequest(Request.Method.DELETE, u2, {
                            response ->

                            orderLineData.clear()
                            arrayUid.clear()
                            getData()
                        }, {
                            Log.e("OrderLineFragment", "Error: $it")
                        })
                        q2.add(s2)

                    } else if (orderlineSize == 0) {
                        Log.i("OrderLineFragment", "No OrderLine in Array")
                    } else {
                        val q1 = Volley.newRequestQueue(context)
                        val u1 = "http://192.168.1.121/pos/pos_api/public/orderline/${orderlinedata.id}"
                        val s1 = StringRequest(Request.Method.DELETE, u1, {
                            response ->

                            orderLineData.clear()
                            arrayUid.clear()
                            getData()
                        }, {
                            Log.e("OrderLineFragment", "Error: $it")
                        })
                        q1.add(s1)
                    }

                } else if (state.equals("UpdateButton")) {
                    val id = orderlinedata.id
                    val oid = orderlinedata.oid
                    val uid = orderlinedata.uid
                    val pid = orderlinedata.pid
                    val pNa = orderlinedata.pname
                    val qty = orderlinedata.qty
                    val sto = orderlinedata.stotal

                    val passData = bundleOf("id" to id.toString(), "oid" to oid.toString(), "uid" to uid.toString(), "pid" to pid.toString(), "pNa" to pNa, "qty" to qty.toString(), "sto" to sto.toString())
                    this.findNavController().navigate(R.id.updatefrag, passData)
                }
            }

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }, {
            Log.e("OrderLineFragment", "Error: $it")
        })

        jsonRequest.retryPolicy = DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 0, 1f)
        activity?.let { VolleySingleton.getInstance(it).addToRequestQueue(jsonRequest) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_orderline, container, false)
        return view
    }
}