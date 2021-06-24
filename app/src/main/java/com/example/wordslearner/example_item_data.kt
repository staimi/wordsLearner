package com.example.wordslearner

class example_item_data {

    var WORD1 : String? = null
    var WORD2 : String? = null

    constructor(){}

    /* constructor(ID:Int, DATE:String, WEIGHT:Float){
         this.ID = ID
         this.DATE = DATE
         this.WEIGHT = WEIGHT
     }*/

    constructor(WORD1:String, WORD2:String){
        this.WORD1 = WORD1
        this.WORD2 = WORD2
    }
}