import endpoints from './endpoints.json';


const replacePathParams = (path, params) => {
  if (!params) return path;
  return Object.keys(params).reduce((updatedPath, param) => {
    return updatedPath.replace(`{${param}}`, params[param]);
  }, path);
};

const apiService = async (serviceName, endpointName, params = {}, body = null) => {
  const service = endpoints[serviceName];
  if (!service) {
    throw new Error(`Service ${serviceName} not found`);
  }

  const endpoint = service.endpoints[endpointName];
  if (!endpoint) {
    throw new Error(`Endpoint ${endpointName} not found in ${serviceName}`);
  }

  const baseURL = process.env.REACT_APP_API_GATEWAY;
  const url = baseURL + replacePathParams(endpoint.path, params);
  const method = endpoint.method;

  const options = {
    method,
    headers: {
      'Content-Type': 'application/json',
      'broadcom_communication_token':'ABCD_1234'
    }
    //mode: 'no-cors',
    //credentials: 'include'
  };

  if (method !== 'GET' && method !== 'DELETE') {
    options.body = JSON.stringify(body);
  }

  try {
    console.log("Base URL "+baseURL);
    console.log(" URL "+url);
    console.log(baseURL,url, "   ", options);
    const response = await fetch(url, options);
    if (!response.ok) {
      throw new Error(`Request failed with status ${response.status}`);
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error fetching data:', error);
    throw error;
  }
};

export default apiService;
