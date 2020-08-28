package com.nash.calculator.helper


import com.nash.calculator.InstanceOfClass
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.reflect.InvocationTargetException

open class SegregateNumberAndOperators {

    private var seperatedValue = String()

    private val instanceOfClass = InstanceOfClass

    /**
     * This function is used to separate Values from the expression. After
     *separation the values is stored inside the Value stack
     *@param currentIndex - It Contains the last position of the String
     * @param expression -  It contains the Users expression
     * @author Nash Jacob John
     */
     fun separateNumber(currentIndex: Int, expression: String) {

        try {
            seperatedValue = expression.substring(instanceOfClass.expressionProcessor.firstIndexOfSeparate, currentIndex)
        } catch (e : InvocationTargetException){
            println(e.message.toString())
        }

        instanceOfClass.expressionProcessor.firstIndexOfSeparate = currentIndex + 1
        try {
            val checkValueIsNumber = Double.parseDouble(seperatedValue)
        } catch (e: IllegalArgumentException) {
            instanceOfClass.expressionProcessor.errorMessage = "Error"
            instanceOfClass.operatorStack.removeAll()
            instanceOfClass.valueStack.removeAll()
            instanceOfClass.expressionProcessor.firstIndexOfSeparate = expression.length
            return
        } catch (e: Exception) {
            println(e.message.toString())
        }

        instanceOfClass.valueStack.push(seperatedValue)


        if (currentIndex == expression.length) {

            if (!instanceOfClass.expressionProcessor.flag) {
                while (!instanceOfClass.operatorStack.isEmpty())
                    instanceOfClass.calculateExpression.findSum()
            } else {
                instanceOfClass.expressionProcessor.errorMessage = "Error"
            }

        }
    }
}