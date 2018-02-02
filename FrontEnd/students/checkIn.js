//  variable checkin js implementation

$(document).ready(function(){
  $('#catagory').change(function(){
    $('cp').prop('disabled', false);
    selection = $(this).val();    
    switch(selection)
    { 
      case 'Community Partner':
        $('#communityPartner').show();
        $('#civicAction').hide();
        $('#allianceBuilding').hide();
        $('#ca').prop('disabled', 'disabled');
        $('#ab').prop('disabled', 'disabled');
        break;
      case 'Civic Action':
        $('#communityPartner').hide();
        $('#civicAction').show();
        $('#allianceBuilding').hide();
        $('#ab').prop('disabled', 'disabled');
        $('#cp').prop('disabled', 'disabled');
        break;
        case 'Alliance Building':
        $('#communityPartner').hide();
        $('#civicAction').hide();
        $('#allianceBuilding').show();
        $('#ca').prop('disabled', 'disabled');
        $('#cp').prop('disabled', 'disabled');
        break;
      default:
        $('#communityPartner').hide();
        $('#civicAction').hide();
        $('#allianceBuilding').hide();
    }
  });
});