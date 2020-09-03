package com.prike.tutorship.ui.sign

import android.content.Context
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.TelephonyManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.prike.tutorship.R
import kotlinx.android.synthetic.main.register_phone_fragment.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException

class RegisterPhoneFragment : SignFragmentBase(R.layout.register_phone_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        showProgressRegister(4f)
        val codePair = getCountryZipCode()
        renderCodes(codePair.second)
        etPhoneCode.setText(codePair.first.second, false)

        etPhoneCode.onItemClickListener = AdapterView.OnItemClickListener { parent, _, pos, id ->
            logMessage(parent.getItemAtPosition(pos).toString())
        }


        //setTextTextInput(accountViewModel.getAccountRegister()?.firstName ?: "", etName)

        etPhoneCode.requestFocus()
        //showSoftKeyboard()

        btnNextStep.setOnClickListener {
            nextStep()
            findNav(R.id.action_registerPhoneFragment_to_registerEmailFragment2)
        }

        btnLogin.setOnClickListener {
            hideSoftKeyboard()
            findNav(R.id.action_registerPhoneFragment_to_loginFragment)
        }
    }

    private fun renderCodes(codes: List<String>) {
        etPhoneCode.setAdapter(ArrayAdapter(requireContext(), R.layout.list_item_dropdown, codes))
    }

    private fun getCountryZipCode(): Pair<Pair<String, String>, List<String>> {
        val countryId = ((activity as SignActivity).getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simCountryIso.toUpperCase()
        var countryFlag = false
        var code = "" to ""
        val listCodes = mutableListOf<String>()
        try {
            val xpp = resources.getXml(R.xml.iccs)
            loop@ while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                when (xpp.eventType) {
                    XmlPullParser.START_TAG -> countryFlag = xpp.name == countryId
                    XmlPullParser.TEXT -> {
                        listCodes.add("+${xpp.text}")
                        if (countryFlag) {
                            code = countryId to "+${xpp.text}"
                        }
                    }
                }
                xpp.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }
        return code to listCodes
    }

    private fun nextStep(): Boolean {
        if (etPhoneCode.text.isEmpty() && etPhoneNumber.text.isNullOrEmpty()) return false
        accountViewModel.phoneRegister(etPhoneCode.text.toString() + etPhoneNumber.text?.replace(Regex("[^0-9]"), ""))
        return true
    }

}