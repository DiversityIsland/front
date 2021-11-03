function updateTwoFactorAuth(use2FA) {
   $.ajax('http://localhost:8888/api/update2FA', {
      method: 'POST',
      contentType: "application/json",
      data: JSON.stringify(
         use2FA
      )
   })
}
