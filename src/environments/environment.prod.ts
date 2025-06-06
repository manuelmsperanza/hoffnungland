import config from '../../auth_config_prod.json';

const { domain, clientId, authorizationParams: { audience }, apiUri, errorPath } = config as {
  domain: string;
  clientId: string;
  authorizationParams: { 
    audience?: string;
  };
  apiUri: string;
  errorPath: string;
};

export const environment = {
  production: true,
  auth: {
    domain,
    clientId,
    authorizationParams: {
      ...(audience && audience !== 'YOUR_API_IDENTIFIER' ? { audience } : null),
    },
    redirect_uri: window.location.origin,
    errorPath,
  },
  httpInterceptor: {
    allowedList: [`${apiUri}/*`],
  },
};
