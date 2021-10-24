/******************************************************************************
 Copyright (c) 2020 Wisipsy. All rights reserved.
 WISIPSY PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
******************************************************************************/

/**
 * Contains all the validation fields
 */

/**
 * Validate an email address
 * @param email email value
 * @returns if the email address is in the correct format
 */
export function validateEmail(email: string): boolean {
  const re =
    /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}
