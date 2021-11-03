function updateTwoFactorAuth(use2FA) {
   $.ajax('/api/update2FA', {
      method: 'POST',
      contentType: "application/json",
      data: JSON.stringify(
         use2FA
      )
   })
}
