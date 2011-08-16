app.views.ContactsList = Ext.extend(Ext.TabPanel, {	
	fullscreen: true,
     type: 'dark',
     sortable: true,
     items: [{
         title: 'Tab 1',
         html: '1',
         cls: 'card1 nobackface'
     }, {
         title: 'Tab 2',
         html: '2',
         cls: 'card2 nobackface'
     }, {
         title: 'Tab 3',
         html: '3',
         cls: 'card3 nobackface'
     }]
});