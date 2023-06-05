package com.example.examplemvvm

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Esta sera la primera clase en llamarse cuando se inicie la APP, ya que es una clase
// que extiendo de : "Application()"-> esta es una clase que extiende de la propia
// aplicacion de Android.

// Al usar la etiqueta @HiltAndroidApp , ya se creara todo el codigo necesario para
// la inyeccion de dependencias sin que tengamos que hacer nada, este codigo
// se crea en tiempo de compilacion con lo cual si hay algun error lo veremos
// apenas el proyecto compile.
@HiltAndroidApp
class ExampleMVVMApp: Application()