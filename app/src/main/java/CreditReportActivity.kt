//package com.example.coroutine.pagination
//
//import android.content.DialogInterface
//import android.content.Intent
//import android.os.Bundle
//import android.view.View
//import android.widget.LinearLayout
//import android.widget.RelativeLayout
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AlertDialog
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.DefaultItemAnimator
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.coroutine.R
//
//import org.json.JSONObject
//
//class CreditReportActivity : AppCompatActivity(), VolleyPostNetworkCall.RequestResponseLis,
//    OnCheckStatusClickListener {
//
//    lateinit var reportList: RecyclerView
//    lateinit var list: ArrayList<CreditReportModel>
//    lateinit var adapter: CreditReportAdapter
//    private var PAGE_COUNT = 1
//    private var MAX_PAGE_COUNT = 1
//    private var loading = true
//    var pastVisiblesItems = 0
//    var visibleItemCount: Int = 0
//    var totalItemCount: Int = 0
//    var requestType: String = "credit_report"
//    lateinit var rData: CreditReportModel
//    val checkedItem = intArrayOf(-1)
//    lateinit var updateStatus: String
//
//    private fun init() {
//        reportList = findViewById(R.id.reportList)
//        val tvTitle: TextView = findViewById(R.id.toolbarTitle)
//        tvTitle.setText("Credit Report")
//        val imgBackCon: LinearLayout = findViewById(R.id.imgBackCon)
//        val imgFilter: LinearLayout = findViewById(R.id.imgFilterCon)
//        imgFilter.setOnClickListener {
//            val i = Intent(this, FilterView::class.java)
//            startActivity(i)
//        }
//        list = ArrayList()
//        imgBackCon.setOnClickListener { finish() }
//
//        initReportSlider()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        clearFilter()
//        setContentView(R.layout.activity_credit_report)
//
//        init()
//        requestType = "credit_report"
//        networkCallUsingVolleyApi(Constants.URL.REPORT, true)
//    }
//
//
//    private fun initReportSlider() {
//        adapter = CreditReportAdapter(this, list, this)
//        val mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        reportList.setLayoutManager(mLayoutManager)
//        reportList.setItemAnimator(DefaultItemAnimator())
//        reportList.setAdapter(adapter)
//        reportList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0) { //check for scroll down
//                    visibleItemCount = mLayoutManager.childCount
//                    totalItemCount = mLayoutManager.itemCount
//                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
//                    if (loading && visibleItemCount + pastVisiblesItems >= totalItemCount) {
//                        loading = false
//                        if (PAGE_COUNT < MAX_PAGE_COUNT) {
//                            PAGE_COUNT++
//                            loadMoreLoader(true)
//                            networkCallUsingVolleyApi(Constants.URL.REPORT, false)
//                        }
//                        loading = true
//                    }
//                }
//            }
//        })
//    }
//
//    private fun networkCallUsingVolleyApi(
//        url: String,
//        isLoad: Boolean,
//    ) {
//        if (AppManager.isOnline(this)) {
//            // new VolleyPostNetworkCall(this, this, url, isLoad).netWorkCall(param());
//            VolleyPostNetworkCall(this, this, url, isLoad).netWorkCall(param())
//        } else {
//            Toast.makeText(this, "Network connection error", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private fun param(): Map<String, String> {
//        val map: MutableMap<String, String> = HashMap()
//        map["apptoken"] = SharedPrefs.getValue(this, SharedPrefs.APP_TOKEN)
//        map["user_id"] = SharedPrefs.getValue(this, SharedPrefs.USER_ID)
//        if (requestType.equals("credit_report")) {
//            map["type"] = "creditstatement"
//        } else {
//            map["start"] = "1"
//            map["id"] = rData.id
//            map["status"] = updateStatus
//        }
//
//        return map
//    }
//
//    override fun onSuccessRequest(JSonResponse: String?) {
//        Print.P("Response $JSonResponse")
//
//        loadMoreLoader(false)
//        try {
//            val jsonObject = JSONObject(JSonResponse)
//            if (requestType.equals("credit_report")) {
//                if (jsonObject.has("data")) {
//
//                    var oldDataSize: Int = list.size
//                    list.clear()
//                    val bannerArray = jsonObject.getJSONArray("data")
//                    MAX_PAGE_COUNT = jsonObject.getInt("pages")
//                    list.addAll(AppHandler.parseCreditReport(bannerArray))
//                    adapter.notifyDataSetChanged()
//                    reportList.verticalScrollbarPosition = oldDataSize
//                    if (list.size == 0) {
//                        errorView("Sorry Records are not available !")
//                    } else {
//                        reportList.setVisibility(View.VISIBLE)
//                        val noData: RelativeLayout = findViewById(R.id.noData)
//                        noData.visibility = View.GONE
//                    }
//                }
//            } else {
//                requestType = "credit_report"
//                networkCallUsingVolleyApi(Constants.URL.REPORT, true)
//            }
//
//        } catch (e: Exception) {
//            e.message?.let { errorView(it) }
//        }
//    }
//
//    override fun onFailRequest(msg: String?) {
//        Print.P("Response $msg")
//        errorView(msg!!)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (Constants.IS_RELOAD_REQUEST) {
//            Constants.IS_RELOAD_REQUEST = false
//            PAGE_COUNT = 1
//            MAX_PAGE_COUNT = 1
////            list.clear()
//            networkCallUsingVolleyApi(Constants.URL.REPORT, true)
//        }
//    }
//
//
//    private fun loadMoreLoader(flag: Boolean) {
//        if (flag) {
//            findViewById<View>(R.id.loader).visibility = View.VISIBLE
//        } else {
//            findViewById<View>(R.id.loader).visibility = View.GONE
//        }
//    }
//
//    private fun errorView(string: String) {
//        val tvMsg = findViewById<TextView>(R.id.tvMsg)
//        tvMsg.text = string
//        reportList.setVisibility(View.GONE)
//        findViewById<View>(R.id.noData).visibility = View.VISIBLE
//    }
//
//    private fun clearFilter() {
//        SharedPrefs.setValue(this, SharedPrefs.FILTER_DATE_FROM, "")
//        SharedPrefs.setValue(this, SharedPrefs.FILTER_DATE_TO, "")
//        SharedPrefs.setValue(this, SharedPrefs.REPORT_SEARCH_TEXT, "")
//        SharedPrefs.setValue(this, SharedPrefs.FILTER_STATUS, "")
//    }
//
//    override fun onStatusChangeListener(position: Int, creditData: CreditReportModel) {
//        requestType = "change_status"
//        rData = creditData
//        showAlertDialog()
//    }
//
//    private fun showAlertDialog() {
//        val alertDialog = AlertDialog.Builder(this)
//        alertDialog.setTitle("Select Status")
//        val listItems = arrayOf("Pending", "Success")
//        alertDialog.setSingleChoiceItems(
//            listItems,
//            checkedItem[0],
//            object : DialogInterface.OnClickListener {
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    updateStatus = listItems[which]
//                }
//            })
//
//        alertDialog.setPositiveButton("Proceed", object : DialogInterface.OnClickListener {
//            override fun onClick(dialog: DialogInterface?, which: Int) {
//                networkCallUsingVolleyApi(Constants.URL.CREDIT_TXN_UPDATE, true)
//            }
//
//        })
//
//        val customAlertDialog = alertDialog.create()
//
//        customAlertDialog.show()
//
//    }
//
//}