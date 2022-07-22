package com.example.customviewpoc

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customviewpoc.databinding.TagCompnentBinding

class TagComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    //Se bindea el layout del componente
    private var binding: TagCompnentBinding

    //Se inicializan TypedArray del componente desde la fuente xml
    private val attributes =
        context.theme.obtainStyledAttributes(attrs, R.styleable.TagComponent, 0, 0)

    //Se declaran los atributos de la clase
    private var isIcon: Boolean
    private var tagMessage: String?

    init {
        //se infla el xml del componente
        binding = TagCompnentBinding.inflate(LayoutInflater.from(context), this, true)
        //se obtienen los valores del CustomvIEW
        try {
            tagMessage = attributes.getString(R.styleable.TagComponent_message)
            isIcon = attributes.getBoolean(R.styleable.TagComponent_isIcon, false)
        } finally {
            //Se limpian los atributos que no se usan
            attributes.recycle()
        }
        //Método que valida el tipo de tag a renderizar
        validateTypeTag()
    }

    //valida el tipo de renderizado
    private fun validateTypeTag() {
        if (isIcon) {
            renderIconTag()
        } else {
            renderSingleTag()
        }
    }
    //renderiza el tagComponent sin un icono
    private fun renderSingleTag() {
        binding.imageView.visibility = View.GONE
        binding.message.text = tagMessage
    }

    //Renderiza el tagComponent con un icono
    private fun renderIconTag() {
        binding.imageView.visibility = View.VISIBLE
        binding.message.text = tagMessage
    }

    //Permite cambiar desde una clase el mensaje del tag
    fun tagMessage(tagMessage: String?) {
        this.tagMessage = tagMessage
        binding.message.text = tagMessage
    }

    //Permite ocultar el ícono del tag desde una clase
    fun isIcon(isIcon: Boolean) {
        this.isIcon = isIcon
        validateTypeTag()
    }
}