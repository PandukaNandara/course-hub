import React, { ReactNode } from "react";
/**
 * This refers a component without a layout.
 *
 * @param Component The component that is passed to the layout
 */
interface NoLayoutProps {
  children: ReactNode
}
const NoLayout = ( {children} : NoLayoutProps): JSX.Element => {
  return <>{children}</>;
};

export default NoLayout;