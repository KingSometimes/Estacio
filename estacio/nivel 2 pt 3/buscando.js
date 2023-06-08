var app = new Vue({
    el: '#usuarios',
    data: {
      users: []
    },
    mounted() {
      this.loadUsers();
    },
    methods: {
      loadUsers() {
          $.ajax({
          url: 'https://reqres.in/api/users?per_page=10',
          method: 'GET',
          success: function(response) {
            app.users = response.data;
          }
        });
      }
    }
  });