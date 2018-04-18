package com.example.vtabaran.fm.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.vtabaran.fm.R
import com.example.vtabaran.fm.service.api.request.LoginRequest
import com.example.vtabaran.fm.service.task.LoginTask

class LoginFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val loginEmail = view.findViewById<EditText>(R.id.loginEmail)
            val loginPassword = view.findViewById<EditText>(R.id.loginPassword)

            val loginRequest = LoginRequest(loginEmail.text.toString(), loginPassword.text.toString())

            LoginTask(loginRequest).execute()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        fun newInstance(): LoginFragment {
            return LoginFragment()
        }

    }
}
