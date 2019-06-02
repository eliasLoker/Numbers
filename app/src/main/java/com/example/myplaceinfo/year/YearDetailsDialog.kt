package com.example.myplaceinfo.year

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.DialogFragment
import com.example.myplaceinfo.OnClickDialogCloseButtonListener
import com.example.myplaceinfo.R

/**
 * Created by Alexandr Mikhalev on 29.05.2019.
 *
 * @author Alexandr Mikhalev
 */
class YearDetailsDialog : DialogFragment(), View.OnClickListener {
    private lateinit var closeView: ImageView
    private var infoTextView: TextView? = null
    private lateinit var favouritesToggleButton: ToggleButton
    private var message: String? = ""

    private lateinit var onClickDialogCloseButtonListener: OnClickDialogCloseButtonListener

    private val KEY: String = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            this.message = it.getString(KEY)
        }
        if (parentFragment is OnClickDialogCloseButtonListener) {
            onClickDialogCloseButtonListener = parentFragment as OnClickDialogCloseButtonListener
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view: View = inflater.inflate(R.layout.dialog_year_details, container, false)
        closeView = view.findViewById(R.id.close_view)
        infoTextView = view.findViewById(R.id.error_text_view)
        favouritesToggleButton = view.findViewById(R.id.favourites)

        infoTextView!!.text = message
        closeView.setOnClickListener(this)
        favouritesToggleButton.setOnClickListener(this)
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
        if (p0!!.id == R.id.close_view) {
            onClickDialogCloseButtonListener.onClickCloseButton(favouritesToggleButton.isChecked)
            dialog!!.dismiss()
        }
    }

    fun newInstance(message: String?): YearDetailsDialog {
        val args = Bundle()
        args.putString(KEY, message)
        val fragment = YearDetailsDialog()
        fragment.arguments = args
        return fragment
    }
}