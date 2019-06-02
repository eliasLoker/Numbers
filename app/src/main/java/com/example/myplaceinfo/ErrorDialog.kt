package com.example.myplaceinfo

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment

/**
 * Created by Alexandr Mikhalev on 01.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ErrorDialog: DialogFragment(), View.OnClickListener {

    private lateinit var closeView: ImageView
    private lateinit var errorTextView: TextView

    private var message: String? = ""
    private val KEY = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.message = it.getString(KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view: View = inflater.inflate(R.layout.dialog_error, container, false)
        closeView = view.findViewById(R.id.close_view)
        errorTextView = view.findViewById(R.id.error_text_view)

        closeView.setOnClickListener(this)
        return view
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
        if (p0!!.id == R.id.close_view) {
            dialog!!.dismiss()
        }
    }

    fun newIntstance(message: String?): ErrorDialog {
        val args = Bundle()
        args.putString(KEY, message)
        val fragment = ErrorDialog()
        fragment.arguments = args
        return fragment
    }
}