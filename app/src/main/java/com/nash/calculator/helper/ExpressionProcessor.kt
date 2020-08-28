package com.nash.calculator.helper



import  com.nash.calculator.InstanceOfClass

open class ExpressionProcessor {


    private val instanceOfClass = InstanceOfClass


    private var elementOfInputProcessor = 0
    var  flag : Boolean = false
    var firstIndexOfSeparate = 0
    var errorMessage : String = "Value Stack Is Empty"


    /**
     * This function get the user expression as an String and calculates the
     * sum and returns as String. This function separates operators into operator stack.
     * @param expression - String expression from the user
     * @return  the sum of the expression
     * @author Nash Jacob John
     */
    fun inputProcessor(expression: String): String {

        elementOfInputProcessor = 0
        firstIndexOfSeparate = 0
        flag = false
        errorMessage = ""
        instanceOfClass.valueStack.removeAll()
        instanceOfClass.operatorStack.removeAll()

        while (elementOfInputProcessor < expression.length) {

            if (expression[elementOfInputProcessor] == '+' ||
                expression[elementOfInputProcessor] == '-' ||
                expression[elementOfInputProcessor] == 'x' ||
                expression[elementOfInputProcessor] == '/' ||
                expression[elementOfInputProcessor] == '(' ||
                expression[elementOfInputProcessor] == ')'

            ) {
                if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(4) ||
                    instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(5)
                ) {

                    if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(4)) {


                        flag = true
                        instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                        firstIndexOfSeparate = elementOfInputProcessor + 1
                        ++elementOfInputProcessor
                        continue
                    }
                    else if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()).equals(5)) {

                        if (flag) {
                            flag = false
                            if (!instanceOfClass.valueStack.isEmpty()) {

                                instanceOfClass.segregateNumberAndOperators.separateNumber(elementOfInputProcessor, expression)
                                while (instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) != 4) {
                                    instanceOfClass.calculateExpression.findSum()
                                }
                            } else {

                                instanceOfClass.segregateNumberAndOperators.separateNumber(elementOfInputProcessor, expression)
                            }

                            if(!instanceOfClass.operatorStack.isEmpty())
                            instanceOfClass.operatorStack.pop()

                            ++elementOfInputProcessor
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            continue

                        } else if (!instanceOfClass.operatorStack.isEmpty() && instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()).equals(4)) {

                            instanceOfClass.operatorStack.pop()
                            firstIndexOfSeparate = elementOfInputProcessor + 1
                            ++elementOfInputProcessor
                            continue
                        } else if (!instanceOfClass.operatorStack.isEmpty() && instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) < 4) {
                            while (instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) != 4) {
                                instanceOfClass.calculateExpression.findSum()
                            }
                            continue
                        } else {
                            return "Error"
                        }
                    }

                } else if (instanceOfClass.operatorStack.isEmpty()) {

                    if (instanceOfClass.valueStack.isEmpty() && elementOfInputProcessor == 0) {

                        if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {
                            //firstIndexOfSeparate = elementOfInputProcessor + 1
                            instanceOfClass.operatorStack.removeAll()
                            instanceOfClass.valueStack.removeAll()
                            elementOfInputProcessor = expression.length
                            return "0.0"
                        }

                        ++elementOfInputProcessor
                        continue
                    } else if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                        instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                        firstIndexOfSeparate = elementOfInputProcessor + 1
                        ++elementOfInputProcessor
                        continue
                    } else {
                        instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                    }

                } else if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) >=
                           instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top())
                ) {

                    if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) > 1) {

                        instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())

                        if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                            ++elementOfInputProcessor
                            continue
                        }
                    } else {

                        if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {
                            instanceOfClass.calculateExpression.findSum()
                            instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                            ++elementOfInputProcessor
                            continue

                        }

                        instanceOfClass.segregateNumberAndOperators.separateNumber(elementOfInputProcessor, expression)
                        instanceOfClass.calculateExpression.findSum()
                        instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                        ++elementOfInputProcessor
                        continue
                    }


                } else if (instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) >
                    instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) || flag
                ) {

                    if (instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor - 1].toString()).equals(5)) {

                        if (instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) in 2..3) {
                            instanceOfClass.calculateExpression.findSum()
                        }
                        instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                        elementOfInputProcessor++
                        continue
                    }

                    instanceOfClass.segregateNumberAndOperators.separateNumber(elementOfInputProcessor, expression)
                    instanceOfClass.calculateExpression.findSum()
                    while (!instanceOfClass.operatorStack.isEmpty() &&
                        instanceOfClass.calculatorTokens.getPriorityFormExp(instanceOfClass.operatorStack.top()) >
                        instanceOfClass.calculatorTokens.getPriorityFormExp(expression[elementOfInputProcessor].toString()) && !flag
                    ) {
                        instanceOfClass.calculateExpression.findSum()
                    }
                    instanceOfClass.operatorStack.push(expression[elementOfInputProcessor].toString())
                    ++elementOfInputProcessor
                    continue
                }

                instanceOfClass.segregateNumberAndOperators.separateNumber(elementOfInputProcessor, expression)
            }// if
            else if (elementOfInputProcessor == expression.length - 1) {
                instanceOfClass.segregateNumberAndOperators.separateNumber(expression.length, expression)
            }


            ++elementOfInputProcessor
        } //while

        if (!instanceOfClass.operatorStack.isEmpty() && !flag) {
            while (!instanceOfClass.operatorStack.isEmpty()) {
                if(flag){
                    break
                }else {
                    instanceOfClass.calculateExpression.findSum()
                }
            }
        }

        if (instanceOfClass.valueStack.isEmpty() || flag) {
            return if(errorMessage.isEmpty()){
                errorMessage = "Error"
                errorMessage
            } else {
                errorMessage
            }

        }


        return instanceOfClass.valueStack.top()
    }//fun




}