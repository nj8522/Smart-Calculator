package com.nash.calculator.helper

import com.nash.calculator.InstanceOfClass
import java.lang.Double
import java.lang.Exception
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

open class CalculateExpression {


    private val instanceOfClass = InstanceOfClass

    private var inputOne = 0.0
    private var inputTwo = 0.0

    var flag : Boolean = false



    /**
     * This function is used to calculate the Top two Values from the Values stack with
     * the top Operator from the Operator stack.
     * @author Nash Jacob John
     */
     fun findSum() {


        if (!instanceOfClass.valueStack.isEmpty()) {
            if (instanceOfClass.expressionProcessor.flag &&
                instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) > 1 &&
                instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) < 4
            ) {

                try {
                    inputTwo = Double.parseDouble(instanceOfClass.valueStack.top())
                    instanceOfClass.valueStack.pop()
                    if(!instanceOfClass.valueStack.isEmpty()){
                        inputOne = Double.parseDouble(instanceOfClass.valueStack.top())
                        instanceOfClass.valueStack.pop()
                    } else {
                        instanceOfClass.expressionProcessor.errorMessage = "0.0"
                        instanceOfClass.expressionProcessor.flag = true
                        return
                    }

                } catch (e: IllegalArgumentException) {
                    println("Please Type in Numbers")
                    instanceOfClass.operatorStack.pop()
                    return
                } catch (e: Exception) {
                    println(e.message.toString())
                    instanceOfClass.operatorStack.pop()
                    return
                }
            }
            else if (instanceOfClass.expressionProcessor.flag) {

                return
            }
            else {
                try {
                    inputTwo = Double.parseDouble(instanceOfClass.valueStack.top())
                    instanceOfClass.valueStack.pop()
                    if(!instanceOfClass.valueStack.isEmpty()){
                        inputOne = Double.parseDouble(instanceOfClass.valueStack.top())
                        instanceOfClass.valueStack.pop()
                    } else {
                        instanceOfClass.expressionProcessor.errorMessage = "0.0"
                        instanceOfClass.expressionProcessor.flag = true
                        return
                    }

                } catch (e: IllegalArgumentException) {
                    println("Enter Number")
                    instanceOfClass.operatorStack.pop()
                    return
                } catch (e: Exception) {
                    println(e.message.toString())
                    return
                }
            }

        }
        else {
            println("Empty")
            return
        }


        var result = 0.0

        try {
            when (instanceOfClass.operatorStack.top()) {

                "+" -> result = instanceOfClass.calculatorHelper.addTwoNumber(inputOne, inputTwo)
                "-" -> result = instanceOfClass.calculatorHelper.subTwoNumbers(inputOne, inputTwo)
                "/" -> result = instanceOfClass.calculatorHelper.testCaseDiv(inputOne, inputTwo)!!
                "x" -> result = instanceOfClass.calculatorHelper.multiplyTwoNumbers(inputOne, inputTwo)

            }
        } catch (e: NullPointerException) {
            instanceOfClass.expressionProcessor.errorMessage = "Division by Zero"
            instanceOfClass.operatorStack.removeAll()
            instanceOfClass.valueStack.removeAll()
            return
        }


        instanceOfClass.operatorStack.pop()
        instanceOfClass.valueStack.push(result.toString())

    }//fun


}