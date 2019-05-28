package com.example.myplaceinfo.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import android.view.Gravity
import android.view.WindowManager
import com.example.myplaceinfo.R


/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class NumberDetailsDialog: DialogFragment(), View.OnClickListener {

    private lateinit var closeView: ImageView
    private var infoTextView: TextView? = null
    private var message: String? = ""

    private val KEY: String = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.message = it.getString(KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view: View = inflater.inflate(R.layout.dialog_number_details, container, false)
        closeView = view.findViewById(R.id.close_view)
        infoTextView = view.findViewById(R.id.dialog_header)

        infoTextView!!.text = message
        closeView.setOnClickListener(this)
        return view
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        setWindow()
    }

    private fun setWindow() {
        dialog!!.setCanceledOnTouchOutside(false)
        val width = WindowManager.LayoutParams.MATCH_PARENT
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        val window = dialog!!.window
        window!!.setLayout(width, height)
        window.setGravity(Gravity.CENTER)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id) {
            R.id.close_view -> dialog!!.dismiss()
        }
    }

    fun newInstance(message: String?): NumberDetailsDialog {
        val args = Bundle()
        args.putString(KEY, message)
        val fragment = NumberDetailsDialog()
        fragment.arguments = args
        return fragment
    }
}