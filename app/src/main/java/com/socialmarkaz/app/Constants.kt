package com.socialmarkaz.app

public class Constants  {



    ///////////////////////////// COLLECTIONS NAME //////////////////////////
    public var USER_COLLECTION="User"
    public var ACCOUNTS_COLLECTION="User-Accounts"
    public var PRODUCT_COLLECTION="Products"
    public var SELLER_COLLECTION="Seller"



    ///////////////////////////  KEYS  ///////////////////
    public var USER_EMAIL= "email"
    public var INVESTOR_PIN= "pin"
    public var ACCOUNT_HOLDER= "account_holder"
    public var INVESTOR_ID= "investorID"
    public var type= "investorID"
    public var TRANSACTION_TYPE= "type"


    ///////////////////////////  LOCAL KEYS  ///////////////////
    public var KEY_ACTIVITY_FLOW= "flow"

    ///////////////////////////  LOCAL KEYS Values ///////////////////
    public var VALUE_ACTIVITY_FLOW_USER_DETAILS= "from_user_details"
    public var VALUE_DIALOG_FLOW_USER_ACCOUNT= "from_user"
    public var VALUE_DIALOG_FLOW_INVESTOR_CNIC= "from_investor"
    public var VALUE_DIALOG_FLOW_NOMINEE_BANK= "from_nominee"
    public var VALUE_DIALOG_FLOW_NOMINEE_CNIC= "from_nominee"

    public var VALUE_DIALOG_FLOW_INVESTOR= "from_investor"
    public var VALUE_DIALOG_FLOW_ADMIN= "from_admin"



    //////////////////////////// KEYS VALUES ////////////////////////////////
    public var ADMIN= "Admin"
    public var TRANSACTION_STATUS_PENDING= "Pending"
    public var TRANSACTION_STATUS_APPROVED= "Approved"
    public var TRANSACTION_STATUS_REJECT= "Reject"
    public var PRODUCT_TYPE_REC= "RecProduct"
    public var PRODUCT_TYPE_CART= "Cart"
    public var PRODUCT_TYPE_PURCH= "Purchased"
    public var PRODUCT_TYPE_FOLL= "FollProduct"
    public var TRANSACTION_TYPE_INVESTMENT= "Investment"
    public var PROFIT_TYPE= "Profit"
    public var TAX_TYPE= "Tax"
    public var INVESTOR_STATUS_ACTIVE= "Active"
    public var INVESTOR_STATUS_PENDING= "Pending"
    public var INVESTOR_STATUS_INCOMPLETE= "Incomplete"
    public var INVESTOR_STATUS_BLOCKED= "Blocked"


    ///////////////////////////  MESSAGES ///////////////////////
    public var INVESTOR_LOGIN_MESSAGE= "User login successfully!"
    public var INVESTOR_LOGIN_FAILURE_MESSAGE= "Incorrect PIN!"

    public var NOMINEE_SIGNUP_MESSAGE= "Nominee added successfully!"
    public var USER_SIGNUP_MESSAGE= "User registered successfully!"
    public var USER_SIGNUP_FAILURE_MESSAGE= "Something went wrong!"
    public var SOMETHING_WENT_WRONG_MESSAGE= "Something went wrong!"

    public var ACCOPUNT_ADDED_MESSAGE= "Account Added Successfully!"
    public var USER_GENDER_ADDED_MESSAGE= "User Gender Added Successfully!"


    public var USER_LOCATION_ADDED_MESSAGE= "User Location Added Successfully"
    public var USER_EMAIL_NOT_EXIST= "User(EMAIL) not exist!"
    public var INVESTOR_CNIC_BLOCKED= "User(CNIC) Blocked!"

    ///////////////////////////// Activities/Fragment Flow //////////////////////////

    public var FROM_PROFIT= "ActivityProfit"
    public var FROM_TAX= "ActivityTax"
    public var FROM_INVESTOR_ACCOUNTS= "ActivityInvestorAccounts"
    public var FROM_NEW_WITHDRAW_REQ= "ActivityNewWithdrawReq"
        public var FROM_NEW_INVESTMENT_REQ= "ActivityNewInvestmentReq"
    public var FROM_PENDING_WITHDRAW_REQ= "FragmentPendingWithdrawReq"
    public var FROM_PENDING_INVESTMENT_REQ= "FragmentPendingInvestmentReq"
    public var FROM_APPROVED_WITHDRAW_REQ= "FragmentApprovedWithdrawReq"
    public var FROM_RECOMENDED_PRODUCT= "FragmentRecomendedProduct"




}