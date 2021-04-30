# RES - SMTP Pranks

> By Alec Berney & Nicolas Crausaz

## Introduction

This lab is part of the HEIG-VD's RES course. It consists in the implementation of a "mail prank sending" software.

The objectives of this lab are:
- To become familiar with the *SMTP protocol*, by working with _SMTP formatted_ messages to a server, without using SMTP libraries
- To use client-server testing tools, like *mock server*
- Create and send *forged emails*

## Functionalities

TODO: Explain what and how the app does its job....


## Implementation

Class Diagram

Example of intractions (client-server level)


## Dependencies


## Configure

In order to get yours pranks ready, you'll need to prepare a few contents first.
First, fill the /assets/emails.utf8 file with all the emails you want to use to send and receive pranks.
Then populate the /assets/pranks.json with all your best pranks, each prank *must* have a title and a content.

You'll probably need to edit your configuration now in configs/config.properties:
```
SMTP_DEFAULT_PORT= <Port of your SMTP server | Required>
SMTP_DEFAULT_SERVER= <Address of your SMTP server | Required>
NUMBER_OF_GROUPS= <Number of group / mails that will be send | Required>
CLIENT_NAME= <Name of client app | Required>
MAIL_HIDDEN_SENDER= <Hidden sender of prank mails | Required | Must be a valid address>
MAIL_CC= <Copy recipient of every prank mails | Optionnal>
MAIL_BCC= <Hidden copy recipient of every prank mails | Optionnal>
```

Notice that required values *must* be specified, if not so, the application will notify you to do it properly.

In case you want to test the application without sending real emails, you can run the MockMock server (you will need to specify localhost as your SMTP server in config.properties):

`add Mock mock jar command`


## Start pranking
