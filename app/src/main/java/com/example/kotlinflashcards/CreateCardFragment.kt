package com.example.kotlinflashcards

import android.Manifest

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.kotlinflashcards.database.FlashcardDatabase
import com.example.kotlinflashcards.entities.Flashcard
import kotlinx.android.synthetic.main.fragment_create_card.*
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


class CreateCardFragment : BaseFragment(),
    EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks
{
    private var cardId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cardId = requireArguments().getInt("cardId",-1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_card, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateCardFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (cardId != -1){

            launch {
                context?.let {
                    var flashcards = FlashcardDatabase.getDatabase(it).flashcardDao().getSpecificCard(cardId)
                    et_cardTitle.setText(flashcards.cardTitle)
                    et_cardText.setText(flashcards.cardText)
                }
            }
        }

        img_done.setOnClickListener {
            if (cardId != -1){
                updateCard()
            }else{
                saveNote()
            }
        }

        img_back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun updateCard() {
        launch {

            context?.let {
                var card = FlashcardDatabase.getDatabase(it).flashcardDao().getSpecificCard(cardId)

                card.cardTitle = et_cardTitle.text.toString()
                card.cardText = et_cardText.text.toString()

                FlashcardDatabase.getDatabase(it).flashcardDao().updateCard(card)

                et_cardTitle.setText("")
                et_cardText.setText("")
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun saveNote() {

        if (et_cardTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Card title is Required",Toast.LENGTH_SHORT).show()
        }

        else if (et_cardText.text.isNullOrEmpty()){
            Toast.makeText(context,"Card Description is Required",Toast.LENGTH_SHORT).show()
        }

        else{
            launch {
                var flashcard = Flashcard()
                flashcard.cardTitle = et_cardTitle.text.toString()
                flashcard.cardText = et_cardText.text.toString()

                context?.let {
                    FlashcardDatabase.getDatabase(it).flashcardDao().insertCards(flashcard)
                    et_cardTitle.setText("")
                    et_cardText.setText("")
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
    }

    private fun deleteCard() {
        launch {
            context?.let {
                FlashcardDatabase.getDatabase(it).flashcardDao().deleteSpecificCard(cardId)
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun hasReadStoragePerm():Boolean{
        return EasyPermissions.hasPermissions(requireContext(),Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,requireActivity())
    }

    
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
       if (EasyPermissions.somePermissionPermanentlyDenied(requireActivity(),perms)){
           AppSettingsDialog.Builder(requireActivity()).build().show()
       }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onRationaleDenied(requestCode: Int) {

    }

    override fun onRationaleAccepted(requestCode: Int) {

    }

}

