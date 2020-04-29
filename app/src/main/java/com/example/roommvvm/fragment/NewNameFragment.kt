package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.example.roommvvm.R
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_new_name.*

/**
 * A simple [Fragment] subclass.
 */
class NewNameFragment : Fragment() {

    //pendeklarasian fragment
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel: NewStudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //memanggil view model melalui suatu variabel
        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //membuat aksi apabila button di klik
        button.setOnClickListener {
            //deklarasi untuk field input
            val input = editText.text.toString().trim()

            //apabila field input kosong, maka akan dimunculkan toast
            if (input.isEmpty()) {
                Toast.makeText(activity, "Nama dibutuhkan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //apabila user menginput lebih dari 30 character maka akan dimunculkan toast
            if (input.length > 30) {
                Toast.makeText(activity, "Nama terlalu panjang", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            mViewModel.storeMovie(input)

            //menampilkan toast apabila yang di inputkan tidak kosong dan tidak melebihi 30 character
            Toast.makeText(activity, "$input entered", Toast.LENGTH_SHORT).show()
            //memanggil fungsi goToStudentListFragment yang digunakan untuk memindahkan ke fragment NameListFragment
            listener?.goToStudentListFragment()
        }
    }

    //fungsi yang berjalan pada saat fragment diaktifkan
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    //fungsi yang berjalan pada saat fragment diganti atau direplace
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToStudentListFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewNameFragment()
    }
}
