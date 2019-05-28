package com.example.myplaceinfo.dialogs

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.myplaceinfo.R

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class DatesDetailsDialog : DialogFragment(), View.OnClickListener {
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
        val view: View = inflater.inflate(R.layout.dialog_dates_details, container, false)
        closeView = view.findViewById(R.id.close_view)
        infoTextView = view.findViewById(R.id.dialog_header)

        infoTextView!!.text = message
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
        when (p0!!.id) {
            R.id.close_view -> dialog!!.dismiss()
        }
    }

    fun newInstance(message: String?): DatesDetailsDialog {
        val args = Bundle()
        args.putString(KEY, message)
        val fragment = DatesDetailsDialog()
        fragment.arguments = args
        return fragment
    }
}