/**
 * Modificado 
 */

var tree = [
   {
       text: "<img src='/assets/img/ic_domain_black_18dp.png' alt='img04'> Empresa Número Um",
       nodes: [
         {
             text: "<img src='/assets/img/ic_language_black.png' alt='img04'> Área Um",
             nodes: [
               {
                   text: "<img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 1", "showIcon": true, id : 1
               },
               {
                   text: "<img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 2"
               }
             ]
             
         },
         {
             text: "<img src='/assets/img/ic_language_black.png' alt='img04'> Área Dois",
             nodes: [
               {
                   text: "<img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 1"
               },
               {
                   text: "<img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor 2"
               }
             ]

         }

       ]
   },
   {
       text: "<img src='/assets/img/ic_domain_black_18dp.png' alt='img04'> Empresa Número Dois",
       nodes: [
         {
             text: "<img src='/assets/img/ic_language_black.png' alt='img04'> Área Um",
             nodes: [
               {
                   text: "<img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor A"
               },
               {
                   text: "<img src='/assets/img/ic_settings_remote_black_24dp.png' alt='img04'>Sensor B"
               }
             ]
         }
       ]
   }
  ];


// function getTree() {
//     // Some logic to retrieve, or generate tree structure
//     return tree;
// }
//
//
// var $searchableTree = $('#treeview-searchable').treeview({
//     data: getTree(),
// });
//
// var search = function (e) {
//     var pattern = $('#input-search').val();
//     var options = {
//         ignoreCase: true,
//         exactMatch: false,
//         revealResults: true
//     };
//
//     var results = $searchableTree.treeview('search', [pattern, options]);
//    
// }

 $('#btn-search').on('click', search);
 $('#input-search').on('keyup', search);